const API_TOKEN="RGAPI-1652bc5d-381b-4572-b79e-8740c576c42a"

export function getSumID(){
  const url = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/WUTED?api_key=" + API_TOKEN
  return fetch(url)
  .then((response) => response.json())
  .catch((error) => console.error(error))
}

export function getLeague(id){
  const url = "https://euw1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + id +"?api_key=" + API_TOKEN
  return fetch(url)
  .then((response) => response.json())
  .catch((error) => console.error(error))
}
