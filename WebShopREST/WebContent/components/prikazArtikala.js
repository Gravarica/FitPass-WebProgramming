import Vue from 'vue'

Vue.Component("prikaz-artikala",{
    data : function(){
        return {
            artikli : null,
            ukupnaCena: 0
        }
    },
    template: 
    `
    <div>
    <h3>Prikaz artikala</h3>
    <table border = "1">
        <tr bgcolor = "lightgrey">
            <th>Sifra</th>
            <th>Naziv</th>
            <th>Tip</th>
            <th>Cena po komadu</th>
            <th>Kolicina</th>
            <th>Ukupna cena</th>
            <th>Obrisi</th>
        </tr>

        <tr v-for="(a, index) in artikli">
            <td>{{a.id}}</td>
            <td>{{a.name}}</td>
            <td>{{a.type}}</td>
            <td>{{a.price}}</td>
            <td>{{a.quantity}}</td>
            <td>{{getTotalPriceForProduct(a)}}</td>
            <td>
                <button v-on:click = "delete(a.id, index)">Brisanje</button>
            </td>
            <tr>
                <td colspan = "5" align="right">Ukupna cena:</td>
                <td>{{ukupnaCena}}</td>
            </tr>
        </tr>
    </table>
    </div>
    `,
    mounted(){
        axios
            .get('rest/artikli')
            .then((response) => (this.artikli = response.data))
        axios
            .get('rest/artikli/total')
            .then((response) => (this.ukupnaCena = response.data))
    },
    methods: {
        delete: function(id, index){
            r = confirm('Da li ste sigurni?')
            if (r){
                axios
                    .delete('rest/artikli/' + id)
                    .then((response) => (this.artikli.splice(index,1)))
            }
        },
        getTotalPriceForProduct : function(artikal){
            return artikal.price * artikal.quantity
        }
    }
});