import React from 'react'
import { StyleSheet, Image } from 'react-native';
import { createStackNavigator, createBottomTabNavigator } from 'react-navigation'
import Search from '../Components/Search'
import Results from '../Components/Results'
import Settings from '../Components/Settings'

const SearchStackNavigator = createStackNavigator({
  Search: {
    screen: Search,
    navigationOptions:{
      title:'Welcome'
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

const TabNavigator = createBottomTabNavigator(
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
    Settings: {
      screen: Settings,
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
