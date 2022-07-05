import React from 'react'
import {RootState} from "../store";
import {connect} from "react-redux";
import {Navigate, Route, Routes} from "react-router-dom";
import Home from "../features/home/Home";
import Test from "../features/home/Test";
import {User} from "../model/user.model";

const mapState = (state: RootState) => ({
    user: state.auth.user,
});
type Props = {
    user: User|null;
};
const Main = ({user}:Props) => {
    console.log(user);
    if (!user) {
        return <Navigate to="/login" />;
    }
    return (
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/test" element={<Test/>}/>
        </Routes>
    )
}

export default connect(mapState,{})(Main);