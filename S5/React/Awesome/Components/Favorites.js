// Components/Favorites.js

import React from 'react'
import { StyleSheet, Text } from 'react-native'

class Favorites extends React.Component {

  render() {
    return (
      <Text style={styles.text}>Mes Favoris</Text>
    )
  }
}

const styles = StyleSheet.create({
  text:{
    marginTop:20
  }
})

export default Favorites
