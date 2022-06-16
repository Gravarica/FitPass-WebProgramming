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
		{ path: '/', name: 'home', component: Product},
	    { path: '/products', component: Products}
	  ]
});

var app = new Vue({
	router,
	el: '#artikli',
	data: {
	}
});