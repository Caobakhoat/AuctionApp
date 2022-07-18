import React from 'react'
import {useGetAllAuctionsQuery} from "../admin.api";
import Table, {ColumnsType} from "antd/lib/table";
import {Item} from "../../../model/item";
import {Button, Space} from "antd";
import {Auction} from "../../../model/auction";

const columns: ColumnsType<Auction> = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Item',
        dataIndex: 'item',
        key: 'item',
        render:(_,record)=>(
            record.item.name
        ),
    },
    {
        title: 'Init Price',
        dataIndex: 'initPrice',
        key: 'initPrice',
    },
    {
        title: 'Start Date',
        dataIndex: 'timeStart',
        key: 'timeStart',
    },
    {
        title: 'End Date',
        dataIndex: 'timeEnd',
        key: 'timeEnd',
    },
    {
        title: 'Status',
        dataIndex: 'status',
        key: 'status',
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

const AuctionManage = () => {
    const {data:auctions,isFetching:isGettingAllAuctions}=useGetAllAuctionsQuery();
    return (
        <>
            <div className="fw-700 fs-50 mb-32">Auction Manage</div>
            <div className="mt-20 mb-20">
                <Button className="bg-green-100 text-white border-radius-sm">Add</Button>
            </div>
            <Table columns={columns} dataSource={auctions?.result.map((el, idx) => ({key: idx, ...el}))}/>
        </>
    )
}

export default AuctionManage;