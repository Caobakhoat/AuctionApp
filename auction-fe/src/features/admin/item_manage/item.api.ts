import {appApi} from "../../../api";
import {BaseResponse} from "../../../model/user";
import {Item} from "../../../model/item";

export const itemApi = appApi.injectEndpoints({
    endpoints: (builder) => ({
        getAllItems: builder.query<BaseResponse<Item[]>, void>({
            query: () => ({
                url: 'item/getAllItems'
            }),
            providesTags: ['Item'],
        }),
        addItem: builder.mutation<BaseResponse<Item>, { name: string, description: string, imageItem: File }>({
            query(data) {
                const formData = new FormData();
                formData.append("name", data.name);
                formData.append("description", data.description);
                formData.append("imageItem", data.imageItem);
                return {
                    url: 'item/addItem',
                    method: "POST",
                    body: formData
                }
            },
            invalidatesTags: ['Item'],
        }),
    }),
})
export const {
    useGetAllItemsQuery,
    useAddItemMutation,
} = itemApi;