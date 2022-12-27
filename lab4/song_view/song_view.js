import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySong();
});

/**
 * Fetches single song release and modifies the DOM tree in order to display it.
 */
function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySong(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display artists.
 *
 * @param {{name: string, artist: string, producer:string, 
 * length: string, releasedate: string, url:string}} song
 */
function displaySong(song) {
    setTextNode('name', song.name);
    setTextNode('artist', song.artist);
    setTextNode('producer', song.producer);
    setTextNode('length', song.length);
    setTextNode('releasedate', song.releasedate);
    setTextNode('url', song.url);
}
