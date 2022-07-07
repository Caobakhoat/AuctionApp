import React from 'react'
import {useNavigate} from "react-router-dom";
import {Card, Col, Row} from "antd";
import {RootState} from "../../store";
import {User} from "../../model/user.model";
import {connect} from "react-redux";
import Search from "antd/lib/input/Search";

const mapState = (state: RootState) => ({
    user: state.auth.user,
});
type Props = {
    user: User|null;
};
const Home = ({user}:Props) => {
    console.log(user);
    const navigate=useNavigate();
    const toAdminPage=()=>{
        navigate("/admin");
    }
    return (
        <>
            <div className="h-200 text-center bg-light-blue-100 d-flex justify-center items-center">
                <div className="fs-100 fw-700 text-white ">Auction App</div>
                {/*<img src={require("../../assets/img/auctionbanner.jpg")} width={"100%"} height={"300px"} alt="#"/>*/}
            </div>
            <div className="h-62 d-flex items-center justify-space-between bg-deep-blue-100">
                <div className="logo ml-32 cursor-pointer" onClick={()=>toAdminPage()}>
                    <img src={require("../../assets/img/home.png")} width={40} height={40} alt="Home" />

                    {/*<Button onClick={()=>toAdminPage()}>Admin</Button>*/}
                </div>
                <div className="search w-500 ml-200">
                    <Search placeholder="input search text" allowClear size="large" enterButton />
                </div>
                <div className="user mr-32">
                    {user? (<div className="text-white fs-24 cursor-pointer">{user?.name}</div>): (<div className="d-flex">
                        <div onClick={()=>{navigate("/login")}} className="text-white fs-24 cursor-pointer mr-20">Login</div>
                        <div onClick={()=>{navigate("/register")}} className="text-white fs-24 cursor-pointer">Register</div>
                    </div>)}
                </div>
            </div>
            <Row gutter={32} className="py-32 px-100 m-0">
                <Col className="gutter-row" span={6}>
                    <div className="text-center p-16">
                        <Card
                            hoverable
                            cover={<img src={require("../../assets/img/binhco100tuoi.jpeg")} className="min-h-275" alt="Item" />}
                            className="border-radius-md "
                        >
                            <h3>Binh co 100 tuoi</h3>
                        </Card>

                    </div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div className="text-center p-16">
                        <Card
                            hoverable
                            cover={<img src={require("../../assets/img/bathuongco.jpg")} className="min-h-275" alt="Item" />}
                            className="border-radius-md"
                        >
                            <h3>Bat huong co 100 tuoi</h3>
                        </Card>

                    </div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div className="text-center p-16">
                        <Card
                            hoverable
                            cover={<img src={require("../../assets/img/binhco100tuoi.jpeg")} className="min-h-275" alt="Item" />}
                            className="border-radius-md "
                        >
                            <h3>Binh co 100 tuoi</h3>
                        </Card>

                    </div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div className="text-center p-16">
                        <Card
                            hoverable
                            cover={<img src={require("../../assets/img/binhco100tuoi.jpeg")} className="min-h-275" alt="Item" />}
                            className="border-radius-md "
                        >
                            <h3>Binh co 100 tuoi</h3>
                        </Card>

                    </div>
                </Col>
            </Row>
        </>
    )
}

// export default Home;
export default connect(mapState,{})(Home);