import React, {useState} from 'react';
import { useHistory } from 'react-router-dom';

const bookAdd = (props) => {

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

        const name = formData.name;
        const category = formData.category;
        const availableCopies = formData.availableCopies;
        const author = formData.author;
        //(name, category, author, availableCopies)
        props.onAddBook(name, category, author, availableCopies);
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
                               required
                               placeholder={"Enter Book name"}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="number"
                               className={"form-control"}
                               id={"availableCopies"}
                               name={"availableCopies"}
                               required
                               placeholder={"Enter available copies"}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label>Category</label>
                        <select name="category" className={"form-control"} onChange={handleChange}>
                            {props.categories.map((category) =>
                                <option value={category}>{category}</option>
                            )}
                        </select>
                    </div>
                    <div className={"form-group"}>
                        <label>Author</label>
                        <select name="author" className={"form-control"} onChange={handleChange}>
                            {props.authors.map((author) =>
                                <option value={author.id}>{author.name} {author.surname}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );

}

export default bookAdd;