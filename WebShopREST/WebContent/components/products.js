Vue.component("products", { 
	data : function(){
        return {
            objects : null,
            ukupnaCena: 0
        }
    },
    template: 
    `
    <div>
    <h3>Sportski objekti</h3>
    <table class="table">
  		<thead class="thead-dark">
    		<tr>
		      <th scope="col">Naziv</th>
		      <th scope="col">Tip</th>
		      <th scope="col">Status</th>
		      <th scope="col">Adresa</th>
		      <th scope="col">Ocena</th>
		    </tr>
 		</thead>
		<tbody>
        <tr v-for="o in objects">
            <td>{{o.name}}</td>
            <td>{{convertType(o)}}</td>
            <td>{{convertStatus(o)}}</td>
            <td>{{o.location.address.street}}</td>
            <td>{{o.averageGrade}}</td>
        </tr>
        </tbody>
    </table>
    </div>
    `,
    mounted(){
        axios
            .get('rest/sport_objects/get')
            .then((response) => (this.objects = response.data))
    },
    methods: {
        deleteArtikal: function(id, index){
            r = confirm('Da li ste sigurni?')
            if (r){
                axios
                    .delete('rest/artikli/' + id)
                    .then((response) => (this.artikli.splice(index,1), this.ukupnaCena = this.ukupnaCena - response.data.price*response.data.quantity ))
					
				
            }
        },
        getTotalPriceForProduct : function(artikal){
            return artikal.price * artikal.quantity
        },
        convertStatus : function (objekat) {
        	if (objekat.status == true){
        		return "Otvoren"
        	} 
        	return "Zatvoren"
        },
        convertType : function (objekat) {
        	if (objekat.sportObjectType == "GYM"){
        		return "Teretana"
        	} else if (objekat.sportObjectType == "POOL"){
        		return "Bazen"
        	} else {
        		return "Sportski centar"
        	}
        },
        getAddress : function(objekat) {
        	return objekat.location.address.street + objekat.loaction.address.number
        }
    }
});