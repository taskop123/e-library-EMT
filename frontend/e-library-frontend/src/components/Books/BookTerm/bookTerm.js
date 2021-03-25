import React from 'react';
import {Link} from "react-router-dom";

const bookTerm = (props) => {

    return(
      <tr>
          <td scope={"col"}>{props.book.name}</td>
          <td scope={"col"}>{props.book.author.name}</td>
          <td scope={"col"}>{props.book.author.surname}</td>
          <td scope={"col"}>{props.book.category}</td>
          <td scope={"col"}>{props.book.availableCopies}</td>
          <Link className={"btn btn-danger"}
                title={"Delete"}
                onClick={() => props.onDelete(props.book.id)}
          >Delete</Link>
          <Link className={"btn btn-warning ml-1"}
              title={"Reserve"}
              onClick={() => props.onMarkAsTaken(props.book.id)}
          >Reserve Book</Link>
          <Link className={"btn btn-info ml-1"}
                title={"Edit"}
                onClick={() => props.onEdit(props.book.id)}
                to={`/books/edit/${props.book.id}`}
          >Edit</Link>
      </tr>
    );

}

export default bookTerm;
