import React, { useEffect, useState } from 'react';
import axios from "axios";
import {Link, useParams} from "react-router-dom";

export default function ViewUser() {

    const [userInfo,setUser]=useState({
        user:"",
        name:"",
        email:""

    })

    const {id}=useParams();

    useEffect(()=>{
        loadUser()
    },[])

    const loadUser =async ()=>{
        const result = await axios.get(`http://localhost:8080/user/${id}`)
        setUser(result.data)
    }

  return (
    <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">User Details</h2>
                    <div className="card">
                        <div className="card-header">
                            Details of user id: {userInfo.id}
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <b>User: </b>
                                    {userInfo.user}
                                </li>
                                <li className="list-group-item">
                                    <b>Name: </b>
                                    {userInfo.name}
                                </li>
                                <li className="list-group-item">
                                    <b>Email: </b>
                                    {userInfo.email}
                                </li>
                                        
                            </ul>
                        </div>
                    </div>
                <Link className="btn btn-primary my-2" to ={"/"}>
                    Home    
                </Link>  
            </div>
        </div>
    </div>
  )
}
