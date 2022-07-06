import React from 'react'
import {useGetAllItemsQuery} from "./admin.api";

const ItemManage = () => {
    const {data,isLoading}=useGetAllItemsQuery();
    console.log(data?.result[0].imageItem);
    return (
        <div>
            <img src={data?.result[0].imageItem} alt=""/>
        </div>
    )
}

export default ItemManage;