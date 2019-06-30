import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserBalance, defaultValue } from 'app/shared/model/user-balance.model';

export const ACTION_TYPES = {
  FETCH_USERBALANCE_LIST: 'userBalance/FETCH_USERBALANCE_LIST',
  FETCH_USERBALANCE: 'userBalance/FETCH_USERBALANCE',
  CREATE_USERBALANCE: 'userBalance/CREATE_USERBALANCE',
  UPDATE_USERBALANCE: 'userBalance/UPDATE_USERBALANCE',
  DELETE_USERBALANCE: 'userBalance/DELETE_USERBALANCE',
  RESET: 'userBalance/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserBalance>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type UserBalanceState = Readonly<typeof initialState>;

// Reducer

export default (state: UserBalanceState = initialState, action): UserBalanceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERBALANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERBALANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERBALANCE):
    case REQUEST(ACTION_TYPES.UPDATE_USERBALANCE):
    case REQUEST(ACTION_TYPES.DELETE_USERBALANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERBALANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERBALANCE):
    case FAILURE(ACTION_TYPES.CREATE_USERBALANCE):
    case FAILURE(ACTION_TYPES.UPDATE_USERBALANCE):
    case FAILURE(ACTION_TYPES.DELETE_USERBALANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERBALANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERBALANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERBALANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_USERBALANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERBALANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/user-balances';

// Actions

export const getEntities: ICrudGetAllAction<IUserBalance> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_USERBALANCE_LIST,
  payload: axios.get<IUserBalance>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IUserBalance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERBALANCE,
    payload: axios.get<IUserBalance>(requestUrl)
  };
};

export const getOwnerBalance: ICrudGetAction<IUserBalance> = id => {
  const requestUrl = `${apiUrl}/owner`;
  return {
    type: ACTION_TYPES.FETCH_USERBALANCE,
    payload: axios.get<IUserBalance>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserBalance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERBALANCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserBalance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERBALANCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserBalance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERBALANCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
