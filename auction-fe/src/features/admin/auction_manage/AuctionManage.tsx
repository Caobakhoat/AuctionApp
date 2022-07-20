import React, {useState} from 'react'
import {useGetAllAuctionsQuery} from "../admin.api";
import Table, {ColumnsType} from "antd/lib/table";
import {Button, Form, Input, Modal, Space, Upload} from "antd";
import {UploadOutlined} from "@ant-design/icons";
import {Auction} from "../../../model/auction.model";

const columns: ColumnsType<Auction> = [
    {
        title: 'Item',
        dataIndex: 'item',
        key: 'item',
    },
    {
        title: 'Start at',
        dataIndex: 'timeStart',
        key: 'timeStart',
    },
    {
        title: 'End at',
        dataIndex: 'timeEnd',
        key: 'timeEnd',
    },
    {
        title: 'Init price',
        dataIndex: 'initPrice',
        key: 'initPrice',
    },
    {
        title: 'Current price',
        dataIndex: 'currentPrice',
        key: 'currentPrice',
    },
    {
        title: 'Host',
        dataIndex: 'host',
        key: 'host',
    },
    {
        title: 'Status',
        dataIndex: 'status',
        key: 'status',
    },
    {
        title: 'Action',
        // dataIndex: 'role',
        key: 'action',
        render: (_, record) => (
            <Space size="middle">
                Edit|Delete
            </Space>
        ),
    },
];

const AuctionManage = () => {
    const [isShowAddAuctionModal,setIsShowAddAuctionModal]=useState(false);
    const {data,isLoading}=useGetAllAuctionsQuery();
    return (
        <>
            <div className="fw-700 fs-50 mb-32">Auction Manage</div>
            <div className="mt-20 mb-20">
                <Button className="bg-green-100 text-white border-radius-sm" onClick={()=>setIsShowAddAuctionModal(true)}>Add</Button>
            </div>
            {/* <Modal title="Add Auction" visible={isShowAddAuctionModal} onOk={()=>setIsShowAddAuctionModal(false)} onCancel={()=>setIsShowAddAuctionModal(false)}>
                <Form
                    name="basic"
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    initialValues={{ remember: true }}
                    onFinish={(values)=>{
                        console.log(values);
                    }}
                    // onFinishFailed={onFinishFailed}
                    autoComplete="off"
                >
                    <Form.Auction
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input your name!' }]}
                    >
                        <Input />
                    </Form.Auction>
                    <Form.Auction
                        label="Description"
                        name="description"
                        rules={[{ required: true, message: 'Please input your description!' }]}
                    >
                        <Input />
                    </Form.Auction>
                    <Upload
                        action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                        listType="picture"
                        maxCount={1}
                    >
                        <Button icon={<UploadOutlined />}>Image Auction</Button>
                    </Upload>
                    <Form.Auction wrapperCol={{ offset: 8, span: 16 }}>
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Auction>
                </Form>
            </Modal> */}
            <Table columns={columns} dataSource={data?.result}/>
        </>
    )
}

export default AuctionManage;