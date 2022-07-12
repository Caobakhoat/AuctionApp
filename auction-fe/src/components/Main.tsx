import React from 'react'
import {RootState} from "../store";
import {connect} from "react-redux";
import {Navigate, Route, Routes} from "react-router-dom";
import Test from "../features/home/Test";
import {User} from "../model/user.model";
import Admin from "../features/admin/Admin";
import UserManage from "../features/admin/user_manage/UserManage";
import ItemManage from "../features/admin/item_manage/ItemManage";
import AuctionManage from "../features/admin/auction_manage/AuctionManage";

const mapState = (state: RootState) => ({
    user: state.auth.user,
});
type Props = {
    user: User|null;
};
const Main = ({user}:Props) => {
    if (!user) {
        return <Navigate to="/login" />;
    }
    return (
        <Routes>
            <Route path="/test" element={<Test/>}/>
            <Route path="/admin" element={<Admin/>}/>
            <Route path="/admin/user-manage" element={<UserManage/>}/>
            <Route path="/admin/item-manage" element={<ItemManage/>}/>
            <Route path="/admin/auction-manage" element={<AuctionManage/>}/>
        </Routes>
    )
}

export default connect(mapState,{})(Main);