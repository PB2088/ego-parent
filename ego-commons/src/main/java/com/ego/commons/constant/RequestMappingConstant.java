package com.ego.commons.constant;

/**
 * 请求URL常量类
 *
 * @author boge.peng
 * @create 2019-03-12 10:47
 */
public final class RequestMappingConstant {
    private RequestMappingConstant() {
    }

    public static class Manage {

        /**
         * 显示商品列表
         */
        public static final String ITEM_LIST = "item/list";

        /**
         * 商品编辑
         */
        public static final String REST_PAGE_ITEM_EDIT = "rest/page/item-edit";

        /**
         * 商品删除
         */
        public static final String REST_ITEM_DELETE = "rest/item/delete";

        /**
         * 商品下架
         */
        public static final String REST_ITEM_INSTOCK = "rest/item/instock";

        /**
         * 商品上架
         */
        public static final String REST_ITEM_RESHELF = "rest/item/reshelf";

        /**
         * 显示商品类目
         */
        public static final String ITEM_CAT_LIST = "item/cat/list";

        /**
         * 图片上传
         */
        public static final String PIC_UPLOAD = "pic/upload";

        /**
         * 新增商品
         */
        public static final String ITEM_SAVE = "item/save";

        /**
         * 规格参数列表
         */
        public static final String ITEM_PARAM_LIST = "item/param/list";

        /**
         * 规格参数删除
         */
        public static final String ITEM_PARAM_DELETE = "item/param/delete";

        /**
         * 点击商品类目按钮显示添加分组按钮
         */
        public static final String ITEM_PARAM_QUERY_ITEMCATID = "item/param/query/itemcatid/{catId}";

        /**
         * 商品规格新增
         */
        public static final String ITEM_PARAM_SAVE = "item/param/save/{catId}";

        /**
         * 网站内容分类列表
         */
        public static final String CONTENT_CATEGORY_LIST = "content/category/list";

        /**
         * 网站内容分类新增
         */
        public static final String CONTENT_CATEGORY_CREATE = "content/category/create";

        /**
         * 网站内容分类重命名
         */
        public static final String CONTENT_CATEGORY_UPDATE = "content/category/update";

        /**
         * 网站内容分类删除
         */
        public static final String CONTENT_CATEGORY_DELETE = "content/category/delete";

        /**
         * 网站内容列表
         */
        public static final String CONTENT_QUERY_LIST = "content/query/list";

        /**
         * 网站内容新增
         */
        public static final String CONTENT_SAVE = "content/save";

        /**
         * 网站内容删除
         */
        public static final String CONTENT_DELETE = "content/delete";
    }

    public static class Item {
        /**
         * Portal菜单显示
         */
        public static final String REST_ITEMCAT_ALL = "rest/itemcat/all";

        /**
         * 显示商品详情
         */
        public static final String SHOW_ITEM_DETAIL = "item/{id}.html";

        /**
         * 显示商品描述
         */
        public static final String SHOW_ITEM_DESC = "item/desc/{id}.html";

        /**
         * 显示商品参数
         */
        public static final String SHOW_ITEM_PARAM = "item/param/{id}.html";
    }

    public static class Search {
        /**
         * 初始化solr数据
         */
        public static final String SOLR_INIT = "solr/init";
        /**
         * 商品搜索
         */
        public static final String SEARCH = "search.html";

        /**
         * 新增商品(solr数据同步)
         */
        public static final String SOLR_ADD = "solr/add";
    }

    public static class Passport {
        /**
         * 显示登陆页面
         */
        public static final String USER_SHOW_LOGIN = "user/showLogin";

        /**
         * 用户登陆
         */
        public static final String USER_LOGIN = "user/login";

        /**
         * 获取用户信息
         */
        public static final String USER_TOKEN = "user/token/{token}";

        /**
         * 用户退出登陆
         */
        public static final String USER_LOGOUT = "user/logout/{token}";
    }

    public static class Cart {
        /**
         * 添加购物车
         */
        public static final String ADD_CART = "cart/add/{id}.html";

        /**
         * 显示购物车
         */
        public static final String SHOW_CART_CART = "cart/cart.html";

        /**
         * 更新购物车商品数量
         */
        public static final String UPDATE_CART = "cart/update/num/{id}/{num}.action";

        /**
         * 删除购物车商品
         */
        public static final String DELETE_CART = "cart/delete/{id}.action";
    }

    public static class Order {
        /**
         * 显示订单确认页面商品信息
         */
        public static final String SHOW_CART_ORDER = "order/order-cart.html";

        /**
         * 创建订单
         */
        public static final String CREATE_ORDER = "order/create.html";
    }

}
