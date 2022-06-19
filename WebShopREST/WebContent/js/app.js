/*
	Ovde definisem komponente
*/ 
const ArtikliForma = { template: '<unos-artikla></unos-artikla>'}
const PrikazArtikala = { template: '<prikaz-artikala></prikaz-artikala>'}
const Product = { template: '<edit-product></edit-product>' }
const Products = { template: '<products></products>' }
const Login = {template: '<login></login>' }
const SearchProduct = {template: '<search></search>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Products},
	    { path: '/product', component: Product},
	    { path: '/login', component: Login }
	  ]
});

var app = new Vue({
	router,
	el: '#main',
	data: {
		userStatus = 'NEREGISTROVAN'
	},
	methods: {
		
	}
});