import {BaseResponse, User} from "../../model/user.model";
import {appApi} from "../../api";
import {Item} from "../../model/item.model";

export const adminApi=appApi.injectEndpoints({
    endpoints: (builder) => ({
        getAllUsers: builder.query<BaseResponse<User[]>, void>({
            query: () => ({
                url: 'user/getAllUser'
            }),
        }),
        getAllItems:builder.query<BaseResponse<Item[]>,void>({
            query:()=>({
                url:'item/getAllItem'
            })
        })
    }),
})
export const {useGetAllUsersQuery,useGetAllItemsQuery} = adminApi;