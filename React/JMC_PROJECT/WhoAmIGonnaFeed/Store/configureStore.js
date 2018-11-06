import {createStore} from 'redux';
import modifySearchHistory from './Reducers/SearchHistoryReducer'


export default createStore(modifySearchHistory)
