import React from 'react'
import {useNavigate} from "react-router-dom";
import {Button} from "antd";

const Home = () => {
    const navigate=useNavigate();
    const test=()=>{
        navigate("/test");
    }
    return (
        <>
            <Button onClick={()=>test()}>Test</Button>
            <div>Home</div>
        </>
    )
}

export default Home;