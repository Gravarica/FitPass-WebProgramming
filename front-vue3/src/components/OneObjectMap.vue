<template>
    <div ref="map-root"
         style="width:100%; height: 100%">
    </div>
</template>


<script>
     import {ref} from 'vue'
    import View from 'ol/View'
    import Map from 'ol/Map'
    import TileLayer from 'ol/layer/Tile'
    import OSM from 'ol/source/OSM'
    import VectorLayer from 'ol/layer/Vector'
    import VectorSource from 'ol/source/Vector'
    import ol from 'ol'
    import Style from 'ol/style/Style'
    import Icon from 'ol/style/Icon'
    import Point from 'ol/geom/Point'
    import Feature from 'ol/Feature'
    import TileJSON from 'ol/source/TileJSON'
    import {transform} from 'ol/proj'

    import 'ol/ol.css'
    export default {
        name: 'MapContainer',
        components: {},
        props: {},
        mounted() {
        // this is where we create the OpenLayers map

        const iconFeature = new Feature({
             geometry: new Point(transform([19.848, 45.251], 'EPSG:4326', 'EPSG:3857')),
             name: 'Ozzy Gym'
        })

        const iconStyle = new Style({
            image: new Icon({
                anchor: [0.5, 0.5],
                anchorXUnits: 'fraction',
                anchorYUnits: 'fraction',
                src: './../assets/point1.png'
            })
        })

        iconFeature.setStyle(iconStyle);

        const vectorSource = new VectorSource({
            features: [iconFeature]
        })

        const vectorLayer = new VectorLayer({
            source: vectorSource,
        })

        const rasterLayer = new TileLayer({
            source: new OSM()
            })

        var map = new Map({
                // the map will be created using the 'map-root' ref
                target: this.$refs['map-root'],
                layers: [
                    vectorLayer,rasterLayer
                ],

                // the map view will initially show the whole world
                view: new View({
                zoom: 16,
                center: [19.848, 45.251],
                projection: 'EPSG:4326',
                constrainResolution: true
                }),
        })

        },
  }
</script>


<style>
</style>