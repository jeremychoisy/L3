import React from 'react'
import {StyleSheet, View, Text, Image, TouchableOpacity} from 'react-native'
import {getImageFromApi} from '../API/TMDBApi'

class FilmItem extends React.Component{


  _displayFavoriteImage(){
    if(this.props.isFilmFavorite){
      return(
        <Image
          style={styles.favorite_image}
          source={require('../Images/ic_favorite.png')}
        />
      )
    }
  }

  render(){
    const {film, displayDetailForFilm}=this.props
    return(
      <TouchableOpacity style={styles.main_container}
        onPress={() => displayDetailForFilm(film.id)}>
        <Image
          style={styles.image}
          source={{uri: getImageFromApi(film.poster_path)}}
          />
        <View style={styles.content}>
          <View style={styles.header}>
            {this._displayFavoriteImage()}
            <Text style={styles.titre} numberOfLines={2}>{film.title}</Text>
            <Text style={styles.vote}> {film.vote_average} </Text>
          </View>
          <View style={styles.description}>
            <Text style={styles.description_text} numberOfLines={6}>{film.overview}</Text>
          </View>
          <View style={styles.date}>
            <Text style={styles.date_text}>Sorti le {film.release_date}</Text>
          </View>
        </View>
      </TouchableOpacity>
    )
  }
}

const styles = StyleSheet.create({
  main_container: {
    height:190,
    flexDirection:'row',
  },
  content: {
    flex:1,
    margin:5
  },
  image:{
    width: 120,
    height: 180,
    margin: 5,
  },
  header:{
    flex:3,
    flexDirection:'row'
  },
  titre:{
    fontWeight: 'bold',
    fontSize: 20,
    flex: 1,
    flexWrap: 'wrap',
    paddingRight: 5
  },
  vote:{
    fontWeight: 'bold',
    fontSize: 26,
    color: '#666666'
  },
  Description:{
    flex:7
  },
  descrption_text:{
    fontStyle: 'italic',
    color: '#666666'
  },
  date:{
    flex:1
  },
  date_text:{
    textAlign: 'right',
    fontSize: 14
  },
  favorite_image:{
    width:25,
    height:25
  }
})

export default FilmItem
