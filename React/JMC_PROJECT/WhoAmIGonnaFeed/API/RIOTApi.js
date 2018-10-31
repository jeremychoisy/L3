const API_TOKEN="RGAPI-1515924c-a430-448c-88f2-a98680058452";
const version = "8.21.1";
// data

export function getSum(name){
  const url = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + API_TOKEN;
  return fetch(url)
  .then((response) => response.json())
  .catch((error) => console.error(error))
}

export function getLeague(id){
  const url = "https://euw1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + id +"?api_key=" + API_TOKEN;
  return fetch(url)
  .then((response) => response.json())
  .catch((error) => console.error(error))
}

// Icon

export function getSumIcon(id){
  return "http://ddragon.leagueoflegends.com/cdn/" + version + "/img/profileicon/" + id + ".png";
}

export function getChampIcon(name){
  return "http://ddragon.leagueoflegends.com/cdn/" + version + "/img/champion/" + name +".png";

}

export function getRuneIcon(name){
  return "https://ddragon.leagueoflegends.com/cdn/img/" + name;

}
