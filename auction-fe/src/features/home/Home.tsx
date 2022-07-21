import React from "react";
import {useNavigate} from "react-router-dom";
import {Avatar, Card, Col, Row} from "antd";
import {connect} from "react-redux";
import Search from "antd/lib/input/Search";
import home from "../../assets/img/home.png";
import {RootState} from "../../store";
import {User} from "../../model/user";
import {useGetAllAuctionsQuery} from "../admin/auction_manage/auction.api";

const mapState = (state: RootState) => ({
    user: state.auth.user,
});
type Props = {
    user: User | null;
};
const Home = ({ user }: Props) => {
    const navigate = useNavigate();
    const {data: auctions, isFetching: isGettingAllAuctions} = useGetAllAuctionsQuery();
    return (
        <>
            <div className="h-200 text-center bg-light-blue-100 d-flex justify-center items-center">
                <div className="fs-100 fw-700 text-white ">Auction App</div>
            </div>
            <div className="h-62 d-flex items-center justify-space-between bg-deep-blue-100">
                <div
                    className="logo ml-32 cursor-pointer"
                >
                    <img src={home} width={40} height={40} alt="Home" />
                </div>
                <div className="search w-500 ml-200">
                    <Search
                        placeholder="input search text"
                        allowClear
                        size="large"
                        enterButton
                    />
                </div>
                <div className="user mr-32">
                    {user ? (
                        <div className="text-white fs-24 cursor-pointer">
                            <Avatar src={user?.photosImagePath} size={40} className="mr-8"/>
                            {user?.name}
                        </div>
                    ) : (
                        <div className="d-flex">
                            <div
                                onClick={() => {
                                    navigate("/login");
                                }}
                                className="text-white fs-24 cursor-pointer mr-20"
                            >
                                Login
                            </div>
                            <div
                                onClick={() => {
                                    navigate("/register");
                                }}
                                className="text-white fs-24 cursor-pointer"
                            >
                                Register
                            </div>
                        </div>
                    )}
                </div>
            </div>
            <Row gutter={32} className="py-32 px-100 m-0">
                {auctions?.result.map((auction,key)=>(
                    <Col className="gutter-row" span={6} key={key}>
                        <div className="text-center p-16">
                            <Card
                                hoverable
                                cover={<img src={auction.item.photosImagePath} className="min-h-275" alt="Item" />}
                                className="border-radius-md "
                            >
                                <h3>{auction.item.name}</h3>
                            </Card>
                        </div>
                    </Col>
                ))}
            </Row>
        </>
    );
};

export default connect(mapState, {})(Home);
