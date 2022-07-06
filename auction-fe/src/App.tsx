import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./features/auth/Login";
import Register from "./features/auth/Register";
import Main from "./components/Main";
import 'antd/dist/antd.min.css';
import "./styles/app.scss";
import Home from "./features/home/Home";

function App() {
  return (
    <BrowserRouter>
      <Routes>
          <Route path="/login" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/" element={<Home/>}/>
          <Route path="*" element={<Main/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
