import React from 'react'
import { StyleSheet, Image } from 'react-native';
import { createStackNavigator, createBottomTabNavigator } from 'react-navigation'
import Search from '../Components/Search'
import Results from '../Components/Results'
import MyAccount from '../Components/MyAccount'

const SearchStackNavigator = createStackNavigator({
  Search: {
    screen: Search,
    navigationOptions: {
      title: 'Welcome'
    }
  },
  Results: {
    screen: Results
  }
})

const MoviesTabNavigator = createBottomTabNavigator(
  {
    Search: {
      screen: SearchStackNavigator,
      navigationOptions: {
        tabBarIcon: () => {
          return <Image
            source={require('../Images/ic_search.png')}
            style={styles.icon}/>
        }
      }
    },
    Aboutus: {
      screen: MyAccount,
      navigationOptions: {
        tabBarIcon: () => {
          return <Image
            source={require('../Images/ic_account.png')}
            style={styles.icon}/>
        }
    }
  }
},
  {
    tabBarOptions: {
      activeBackgroundColor: '#DDDDDD', // Couleur d'arrière-plan de l'onglet sélectionné
      inactiveBackgroundColor: '#FFFFFF', // Couleur d'arrière-plan des onglets non sélectionnés
      showLabel: false, // On masque les titres
      showIcon: true // On informe le TabNavigator qu'on souhaite afficher les icônes définis
    }
  }
)

const styles = StyleSheet.create({
  icon: {
    width: 30,
    height: 30
  }
})

export default MoviesTabNavigator
