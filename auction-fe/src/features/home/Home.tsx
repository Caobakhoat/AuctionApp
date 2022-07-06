import React from 'react'
import {useNavigate} from "react-router-dom";
import {Button, Col, Row} from "antd";

const Home = () => {
    const navigate=useNavigate();
    const test=()=>{
        navigate("/admin");
    }
    return (
        <>
            <Button onClick={()=>test()}>Admin</Button>
            <Row gutter={16} className="px-24">
                <Col className="gutter-row" span={6}>
                    <div style={{ background: '#0092ff', padding: '8px 0' }}>col-6</div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div style={{ background: '#0092ff', padding: '8px 0' }}>col-6</div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div style={{ background: '#0092ff', padding: '8px 0' }}>col-6</div>
                </Col>
                <Col className="gutter-row" span={6}>
                    <div style={{ background: '#0092ff', padding: '8px 0' }}>col-6</div>
                </Col>
            </Row>
        </>
    )
}

export default Home;