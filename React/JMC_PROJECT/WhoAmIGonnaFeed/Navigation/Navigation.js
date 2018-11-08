import React from 'react'
import { StyleSheet, Image } from 'react-native';
import { createStackNavigator, createBottomTabNavigator } from 'react-navigation'
import Search from '../Components/Search'
import Results from '../Components/Results'
import SearchHistory from '../Components/SearchHistory'

const MainSearchStackNavigator = createStackNavigator({
  Search: {
    screen: Search,
    navigationOptions:{
      title:'Who am I gonna feed ?'
    }
  },
  Results: {
    screen: Results,
    navigationOptions:{
      title:'Current game'
    }
  }
},
{
  navigationOptions: {
    headerStyle:{height:35}
    }
  }
)

const SecondSearchStackNavigator = createStackNavigator({
  SearchHistory: {
    screen : SearchHistory,
    navigationOptions:{
      title:'Search History'
    }
  },
  Results:{
    screen: Results,
    navigationOptions:{
      title:'Current game'
    }
  }
},
{
  navigationOptions: {
    headerStyle:{height:35}
    }
  }
)

const TabNavigator = createBottomTabNavigator(
  {
    Search: {
      screen: MainSearchStackNavigator,
      navigationOptions: {
        tabBarIcon: () => {
          return <Image
            source={require('../Images/ic_search.png')}
            style={styles.icon}/>
        }
      }
    },
    Settings: {
      screen: SecondSearchStackNavigator,
      navigationOptions: {
        tabBarIcon: () => {
          return <Image
            source={require('../Images/ic_settings.png')}
            style={styles.icon}/>
        }
    }
  }
},
  {
    tabBarOptions: {
      activeBackgroundColor: '#DDDDDD',
      inactiveBackgroundColor: '#FFFFFF',
      showLabel: false,
      showIcon: true,
      style:{height:35}

    }
  }
)

const styles = StyleSheet.create({
  icon: {
    width: 30,
    height: 30
  }
})

export default TabNavigator
