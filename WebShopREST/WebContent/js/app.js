/*
	Ovde definisem komponente
*/ 
const ArtikliForma = { template: '<unos-artikla></unos-artikla>'}
const PrikazArtikala = { template: '<prikaz-artikala></prikaz-artikala>'}
const Product = { template: '<edit-product></edit-product>' }
const Products = { template: '<products></products>' }
const SearchProduct = {template: '<search></search>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Products},
	    { path: '/product', component: Product}
	  ]
});

var app = new Vue({
	router,
	el: '#main',
	data: {
	}
});