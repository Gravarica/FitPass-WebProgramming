<template>
    <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height: 100%">

    <ol-view ref="view" :center="[longitude, latitude]" :rotation="rotation" :zoom="zoom" 
    :projection="projection" />

    <ol-tile-layer>
        <ol-source-osm />
    </ol-tile-layer>

    <ol-vector-layer>

            <ol-source-vector :format="geoJson" :projection="projection">
                
                <ol-feature>
                    
                    <ol-geom-point :coordinates="[longitude, latitude]"></ol-geom-point>
                    <ol-style>
                        <ol-style-icon :src="markerIcon" :scale="0.5"></ol-style-icon>
                    </ol-style>
                </ol-feature>     
            
            </ol-source-vector>
    </ol-vector-layer>

</ol-map>
</template>


<script>
     import {ref, inject} from 'vue'
     import markerIcon from './../assets/point.png'
    
    export default {
        props: {
            longitude : {
                type : Number
            },
            latitude : {
                type: Number
            }
        },
        setup() {
        const projection = ref('EPSG:4326')
        const zoom = ref(16.94)
        const rotation = ref(0)
        const format = inject('ol-format')
        const geoJson = new format.GeoJSON();
        return {
            projection,
            zoom,
            rotation,
            geoJson,
            markerIcon
        }
        }
  }
</script>


<style>
</style>