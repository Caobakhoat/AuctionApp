import React, {useState} from 'react'
import {useGetAllItemsQuery} from "../admin.api";
import Table, {ColumnsType} from "antd/lib/table";
import {Button, Form, Input, Modal, Space, Upload} from "antd";
import {UploadOutlined} from "@ant-design/icons";
import {Item} from "../../../model/item.model";

const columns: ColumnsType<Item> = [
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Description',
        dataIndex: 'description',
        key: 'description',
    },
    {
        title: 'Image',
        dataIndex: 'image',
        key: 'image',
        render:(_,record)=>(
            <img src={record.photosImagePath} height={50} alt="#"/>
        ),
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

const ItemManage = () => {
    const [isShowAddItemModal,setIsShowAddItemModal]=useState(false);
    const {data,isLoading}=useGetAllItemsQuery();
    return (
        <>
            <div className="fw-700 fs-50 mb-32">Item Manage</div>
            <div className="mt-20 mb-20">
                <Button className="bg-green-100 text-white border-radius-sm" onClick={()=>setIsShowAddItemModal(true)}>Add</Button>
            </div>
            <Modal title="Add Item" visible={isShowAddItemModal} onOk={()=>setIsShowAddItemModal(false)} onCancel={()=>setIsShowAddItemModal(false)}>
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
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input your name!' }]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        label="Description"
                        name="description"
                        rules={[{ required: true, message: 'Please input your description!' }]}
                    >
                        <Input />
                    </Form.Item>
                    <Upload
                        action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                        listType="picture"
                        maxCount={1}
                    >
                        <Button icon={<UploadOutlined />}>Image Item</Button>
                    </Upload>
                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
            <Table columns={columns} dataSource={data?.result}/>
        </>
    )
}

export default ItemManage;