import {BaseResponse, User} from "../../model/user.model";
import {appApi} from "../../api";
import {Item} from "../../model/item.model";

export const adminApi=appApi.injectEndpoints({
    endpoints: (builder) => ({
        getAllUsers: builder.query<BaseResponse<User[]>, void>({
            query: () => ({
                url: 'user/getAllUsers'
            }),
        }),
        getAllItems:builder.query<BaseResponse<Item[]>,void>({
            query:()=>({
                url:'item/getAllItems'
            })
        }),
        addItem:builder.mutation<BaseResponse<Item>, { name:string,description:string,imageItem:string }>({
            query:(arg)=>({
                url:'item/addItem',
                method: "POST",
                body: arg,
            })
        })
    }),
})
export const {useGetAllUsersQuery,useGetAllItemsQuery,useAddItemMutation} = adminApi;