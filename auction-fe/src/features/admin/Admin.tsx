import {DesktopOutlined, UnorderedListOutlined, UserOutlined,} from '@ant-design/icons';
import type {MenuProps} from 'antd';
import {Layout, Menu} from 'antd';
import React, {useState} from 'react';
import ItemManage from "./item_manage/ItemManage";
import AuctionManage from "./auction_manage/AuctionManage";
import UserManage from "./user_manage/UserManage";

const { Header, Content, Footer, Sider } = Layout;

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
    label: React.ReactNode,
    key: React.Key,
    icon?: React.ReactNode,
    children?: MenuItem[],
): MenuItem {
    return {
        key,
        icon,
        children,
        label,
    } as MenuItem;
}

const Admin: React.FC = () => {
    const [collapsed, setCollapsed] = useState(false);
    const [menu,setMenu]=useState("item");
    const items: MenuItem[] = [
        getItem(<div onClick={()=>{
            setMenu("item")}}>Item</div>, '1', <UnorderedListOutlined />),
        getItem(<div onClick={()=>{
            setMenu("auction")}}>Auction</div>, '2', <DesktopOutlined />),
        getItem(<div onClick={()=>{
            setMenu("user")}}>User</div>, '3', <UserOutlined />),
    ];
    return (
        <Layout style={{ minHeight: '100vh' }}>
            <Sider collapsible collapsed={collapsed} onCollapse={value => setCollapsed(value)} >
                <div className="h-62 fs-24 d-flex items-center justify-center text-white">Auction Admin</div>
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items}/>
            </Sider>
            <Layout className="site-layout">
                <Header className="site-layout-background" style={{ padding: 0 }} />
                <Content style={{ margin: '0 16px' }}>
                    {/*<Breadcrumb style={{ margin: '16px 0' }}>*/}
                    {/*    <Breadcrumb.Item>User</Breadcrumb.Item>*/}
                    {/*    <Breadcrumb.Item>Bill</Breadcrumb.Item>*/}
                    {/*</Breadcrumb>*/}
                    <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
                        {(menu==="item")&&<ItemManage/>}
                        {(menu==="auction")&&<AuctionManage/>}
                        {(menu==="user")&&<UserManage/>}
                    </div>
                </Content>
                <Footer style={{ textAlign: 'center' }}>Copyright ©2022 CBK ft COI</Footer>
            </Layout>
        </Layout>
    );
};

export default Admin;