import React, {Component} from 'react';
import logo from '../../logo.svg';
import './App.css';
import ELibraryService from '../../repository/eLibraryRepository';
import {BrowserRouter as Router, Route, Redirect} from "react-router-dom";

import Header from '../Header/header';
import Authors from '../Authors/authors';
import Categories from '../Categories/categories';
import Countries from '../Countries/countries';
import Books from '../Books/BookList/books';
import BookAdd from '../Books/BookAdd/bookAdd';
import BookEdit from '../Books/BookEdit/bookEdit';

class App extends Component{

  constructor(props) {
    super(props);

    this.state = {
      categories: [],
      authors: [],
      countries: [],
      books: [],
      selectedBook: {}
    }

  }

  render() {

    return (
        <Router>
          <main>
            <Header/>
            <div className={"container"}>
              <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
              <Route path={"/categories"} exact render={() => <Categories categories={this.state.categories}/>}/>
              <Route path={"/countries"} exact render={() => <Countries countries={this.state.countries}/>}/>
              <Route path={"/books/edit/:id"} exact render={() =>
                  <BookEdit onEditBook={this.editBook}
                            book={this.state.selectedBook}
                            authors={this.state.authors}
                            categories={this.state.categories}
                  />}/>
              <Route path={"/books/add"} exact render={() =>
                  <BookAdd onAddBook={this.addBook}
                           authors={this.state.authors}
                           categories={this.state.categories}/>}/>
              <Route path={"/books"} exact render={() =>
                  <Books onMarkAsTaken={this.markAsTaken}
                         onDelete={this.deleteBook}
                         books={this.state.books}
                         onEdit={this.getBook}/>}/>
              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );

  }

  componentDidMount() {

    this.loadAuthors();
    this.loadBooks();
    this.loadCategories();
    this.loadCountries();

  }

  getBook = (id) => {
      ELibraryService.getBook(id)
          .then((data) => {
              this.setState({
                  selectedBook: data.data
              });
          });
  }

  editBook = (id, name, category, author, availableCopies) => {
      ELibraryService.editBook(id, name, category, author, availableCopies)
          .then(() => {
              this.loadBooks()
          });
  }

  markAsTaken = (id) => {
      ELibraryService.markAsTaken(id)
          .then(() => {
              this.loadBooks();
          });
  }

  addBook = (name, category, author, availableCopies) => {
      ELibraryService.addBook(name, category, author, availableCopies)
          .then(() => {
              this.loadBooks();
          });
  }

  deleteBook = (id) => {
      ELibraryService.deleteBook(id)
          .then(() => {
              this.loadBooks();
          });
  }

  loadAuthors = () => {
    ELibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          });
        });
  }

  loadBooks = () => {
    ELibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          });
        });
  }

  loadCategories = () => {
    ELibraryService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          });
        });
  }

  loadCountries = () => {
    ELibraryService.fetchCountries()
        .then((data) => {
          this.setState({
            countries: data.data
          });
        });
  }

}


export default App;
