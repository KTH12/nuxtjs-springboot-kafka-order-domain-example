export default {
    // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
    ssr: false,

    // Global page headers: https://go.nuxtjs.dev/config-head
    head: {
        title: 'product-client',
        htmlAttrs: {
            lang: 'en'
        },
        meta: [
            {charset: 'utf-8'},
            {name: 'viewport', content: 'width=device-width, initial-scale=1'},
            {hid: 'description', name: 'description', content: ''}
        ],
        link: [
            {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'}
        ]
    },

    // Global CSS: https://go.nuxtjs.dev/config-css
    css: [
        'vuesax/dist/vuesax.css',
        '@/assets/style/main.scss',
        '@/assets/style/reset.css',
    ],

    // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
    plugins: [
        '@/plugins/vuesax'
    ],

    // Auto import components: https://go.nuxtjs.dev/config-components
    components: true,

    // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
    buildModules: [],

    // Modules: https://go.nuxtjs.dev/config-modules
    modules: [],

    // Build Configuration: https://go.nuxtjs.dev/config-build
    build: {},

    router: {
        extendRoutes(routes, resolve) {
            routes.push(
                {
                    name: 'custom',
                    path: '*',
                    component: resolve(__dirname, 'pages/404.vue')
                },
                {
                    name: 'product-list',
                    path: 'product/:_storeId/list',
                    component: 'pages/product/_storeId/list.vue'
                },
                {
                    name: 'store-list',
                    path: 'store/:_storeId/list',
                    component: 'pages/store/_storeId/list.vue'
                },
                {
                    name: 'order-list',
                    path: 'order/:_storeId/order-list',
                    component: 'pages/order/_storeId/order-list.vue'
                },
            )
        }
    }
}
