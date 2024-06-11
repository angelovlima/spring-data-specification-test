# API Spring Data Specification Test

Este projeto demonstra como utilizar a Specification do Spring Data JPA para a realização de filtros.

## Endpoints

### 1. Buscar com Filtros (Sem Paginação)

- **URL**: `/primary/find-with-filters`
- **Método**: `GET`
- **Parâmetros de Consulta**:
  - `id`: (opcional) Filtra pelo ID da entidade primária.
  - `secondaryEntityName`: (opcional) Filtra pelo nome da entidade secundária.
Ex.: http://localhost:8080/primary/find-with-filters?secondaryEntityName=Other

### 2. Buscar com Filtros e Paginação

- **URL**: `/primary/find-with-filters-pageable`
- **Método**: `GET`
- **Parâmetros de Consulta**:
  - `id`: (opcional) Filtra pelo ID da entidade primária.
  - `secondaryEntityName`: (opcional) Filtra pelo nome da entidade secundária.
  - `page`: Número da página (inicia em 0).
  - `size`: Número de itens por página.
  - `sort`: Campo pelo qual deseja ordenar os resultados, seguido pela direção (`asc` ou `desc`).
Ex.: http://localhost:8080/primary/find-with-filters-pageable?id=2&secondaryEntityName=Other&page=0&size=10&sort=id,asc

### 3. Buscar com Paginação (Sem Filtros)

- **URL**: `/primary/find-paginated`
- **Método**: `GET`
- **Parâmetros de Consulta**:
  - `page`: Número da página (inicia em 0).
  - `size`: Número de itens por página.
  - `sort`: Campo pelo qual deseja ordenar os resultados, seguido pela direção (`asc` ou `desc`).
Ex.: http://localhost:8080/primary/find-pageable?page=0&size=10&sort=id,asc

## Usando os Endpoints com Axios

### 1. Buscar com Filtros (Sem Paginação)

import axios from 'axios';

const fetchFilteredData = async () => {
  const params = {
    id: 1,  // ou qualquer ID que você deseja filtrar
    secondaryEntityName: 'example',  // ou qualquer nome que você deseja filtrar
  };

  try {
    const response = await axios.get('http://localhost:8080/primary/find-with-filters', { params });
    console.log(response.data);
  } catch (error) {
    console.error('Error find data:', error);
  }
};

fetchFilteredData();

### 2. Buscar com Filtros e Paginação

import axios from 'axios';

const fetchFilteredAndPaginatedData = async () => {
  const params = {
    id: 1,  // ou qualquer ID que você deseja filtrar
    secondaryEntityName: 'example',  // ou qualquer nome que você deseja filtrar
    page: 0,  // número da página (inicia em 0)
    size: 10,  // número de itens por página
    sort: 'id,asc'  // ordenar pelo campo 'id' em ordem ascendente
  };

  try {
    const response = await axios.get('http://localhost:8080/primary/find-with-filters-pageable', { params });
    console.log(response.data);
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

fetchFilteredAndPaginatedData();

### 3. Buscar com Paginação (Sem Filtros)

import axios from 'axios';

const fetchPaginatedData = async () => {
  const params = {
    page: 0,  // número da página (inicia em 0)
    size: 10,  // número de itens por página
    sort: 'id,asc'  // ordenar pelo campo 'id' em ordem ascendente
  };

  try {
    const response = await axios.get('http://localhost:8080/primary/find-paginated', { params });
    console.log(response.data);
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

fetchPaginatedData();
