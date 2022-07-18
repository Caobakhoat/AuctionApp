import React from 'react'
import {useGetAllUsersQuery} from "../admin.api";
import Table, {ColumnsType} from "antd/lib/table";
import {User} from "../../../model/user";
import {Dropdown, Menu, Space} from "antd";
import {DownOutlined} from "@ant-design/icons";

const menu = (
    <Menu
        items={[
            {
                label: <div>ADMIN</div>,
                key: '0',
            },
            {
                label: <div>GUEST</div>,
                key: '1',
            },
        ]}
    />
);
const columns: ColumnsType<User> = [
    {
        title: 'Username',
        dataIndex: 'username',
        key: 'username',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Address',
        dataIndex: 'address',
        key: 'address',
    },
    {
        title: 'DOB',
        key: 'dob',
        dataIndex: 'dob',
    },
    {
        title: 'Balance',
        dataIndex: 'balance',
        key: 'balance',
    },
    {
        title: 'Role',
        // dataIndex: 'role',
        key: 'role',
        render: (_, record) => (
            <Space size="middle">
                <Dropdown overlay={menu} trigger={['click']}>
                    <a onClick={e => e.preventDefault()}>
                        <Space>
                            {record.role.toUpperCase()}
                            <DownOutlined />
                        </Space>
                    </a>
                </Dropdown>
            </Space>
        ),
    },
];

const UserManage = () => {
    const {data,isLoading}=useGetAllUsersQuery();
    return (
        <>
            <div className="fw-700 fs-50 mb-32">User Manage</div>
            <Table columns={columns} dataSource={data?.result} rowKey="username"/>
        </>
    )
}

export default UserManage;