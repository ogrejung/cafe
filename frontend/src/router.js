
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProductManager from "./components/ProductManager"

import OrderManager from "./components/OrderManager"

import PayManager from "./components/PayManager"

import DeliveryManager from "./components/DeliveryManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/products',
                name: 'ProductManager',
                component: ProductManager
            },

            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },

            {
                path: '/pays',
                name: 'PayManager',
                component: PayManager
            },

            {
                path: '/deliveries',
                name: 'DeliveryManager',
                component: DeliveryManager
            },



    ]
})
