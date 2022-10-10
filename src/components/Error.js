import { Link } from "react-router-dom";

function Error () {
    return ( 
        <div>
            <h2>404</h2>
            <p>page not found</p>
            <Link to='/'>go back</Link>
        </div>
     );
}

export default Error;