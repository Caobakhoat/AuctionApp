import React, {useState} from 'react'
import Table, {ColumnsType} from "antd/lib/table";
import {Button, Form, Input, Modal, Space, Upload} from "antd";
import {UploadOutlined} from "@ant-design/icons";
import {Item} from "../../../model/item";
import {useAddItemMutation, useGetAllItemsQuery} from "./item.api";

const columns: ColumnsType<Item> = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
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
    const {data,isFetching}=useGetAllItemsQuery();
    const [addItem]=useAddItemMutation();
    const [form] = Form.useForm();
    return (
        <>
            <div className="fw-700 fs-50 mb-32">Item Manage</div>
            <div className="mt-20 mb-20">
                <Button className="bg-green-100 text-white border-radius-sm" onClick={()=>setIsShowAddItemModal(true)}>Add</Button>
            </div>
            <Modal title="Add Item" visible={isShowAddItemModal} footer={null} onCancel={()=>setIsShowAddItemModal(false)}>
                <Form
                    form={form}
                    name="basic"
                    labelCol={{ offset:1,span: 5 }}
                    wrapperCol={{ span: 16 }}
                    requiredMark={false}
                    autoComplete="off"
                    onFinish={async (values)=>{
                        const imageItem=values.imageItem.file.originFileObj;
                        const item={...values,imageItem};
                        await addItem(item).unwrap();
                        form.resetFields();
                        setIsShowAddItemModal(false);
                    }}
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
                    <Form.Item
                        name="imageItem"
                        label="Image Item"
                        valuePropName="formList"
                    >
                        <Upload
                            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                            listType="picture"
                            maxCount={1}
                        >
                            <Button icon={<UploadOutlined />}>Image Item</Button>
                        </Upload>
                    </Form.Item>

                    <Form.Item wrapperCol={{span: 24 }} className="text-center">
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
            <Table columns={columns} dataSource={data?.result.map((el, idx) => ({key: idx, ...el}))}/>
        </>
    )
}

export default ItemManage;