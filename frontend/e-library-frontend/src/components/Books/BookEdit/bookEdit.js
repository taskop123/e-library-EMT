import React, {useState} from 'react';
import { useHistory } from 'react-router-dom';

const bookEdit = (props) => {

    const history = useHistory();
    const [formData, setFormData] = useState({
        //name, category, author, availableCopies
        name: "",
        category: "",
        author: 1,
        availableCopies: 0
    });

    const onFormSubmit = (e) => {
        e.preventDefault();

        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "" ? formData.category : props.book.category;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        const author = formData.author !== 1 ? formData.author : props.book.author.id;
        //(name, category, author, availableCopies)
        props.onEditBook(props.book.id, name, category, author, availableCopies);
        history.push("/books");

    }

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        });
    }

    return(
        <div className={"row mt-5"}>
            <div className={"col-md-5"}>
                <form onSubmit={onFormSubmit}>
                    <div className={"form-group"}>
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className={"form-control"}
                               id={"name"}
                               name={"name"}
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="number"
                               className={"form-control"}
                               id={"availableCopies"}
                               name={"availableCopies"}
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label>Category</label>
                        <select name="category" className={"form-control"} onChange={handleChange}>
                            {props.categories.map((category) => {
                                    if (props.book.category !== undefined &&
                                        props.book.category === category) {
                                        return <option value={category} selected={props.book.category}>{category}</option>
                                    } else {
                                        return <option value={category}>{category}</option>
                                    }
                                }
                            )}
                        </select>
                    </div>
                    <div className={"form-group"}>
                        <label>Author</label>
                        <select name="author" className={"form-control"} onChange={handleChange}>
                            {props.authors.map((author) => {

                                    if (props.book.author !== undefined && props.book.author.id === author.id) {
                                        return <option value={author.id}
                                                       selected={props.book.author.id}>{author.name} {author.surname}</option>
                                    } else
                                        return <option value={author.id}>{author.name} {author.surname}</option>
                                }
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );

}

export default bookEdit;