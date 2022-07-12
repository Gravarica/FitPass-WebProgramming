<template>
    <div ref="map-root" style="width:100%; height: 100%">

    </div>
</template>


<script>
  import View from 'ol/View'
  import Map from 'ol/Map'
  import TileLayer from 'ol/layer/Tile'
  import OSM from 'ol/source/OSM'
  import VectorLayer from 'ol/layer/Vector'
  import VectorSource from 'ol/source/Vector'
  import {transform} from 'ol/proj'
  import { on } from 'events'

  // importing the OpenLayers stylesheet is required for having
  // good looking buttons!
  import 'ol/ol.css'

  export default {
    name: 'MapContainer',
    components: {},
    props: {
        geojson : Object
    },
    data(){
        return {
            olMap : null,
            vectorLayer: null
        }    
    },
    mounted() {
        this.vectorLayer = new VectorLayer({
            source: new VectorSource({
                features: [],
            })
        })
      // this is where we create the OpenLayers map
        this.olMap = new Map({
        // the map will be created using the 'map-root' ref
            target: this.$refs['map-root'],
            layers: [
          // adding a background tiled layer
                new TileLayer({
                    source: new OSM() // tiles are served by OpenStreetMap
                }),
            ],

        // the map view will initially show the whole world
            view: new View({
                zoom: 13.94,
                center: [19.848, 45.251],
                projection: 'EPSG:4326',
                constrainResolution: true
            }),
        })

        this.olMap.on('click', (event) => {
            this.$emit('select', event.coordinate)
        })
    },
    watch: {
    
    },
    methods: {
        
    }
  }
</script>


<style>
</style>