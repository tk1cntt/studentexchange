import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICurrencyRate, defaultValue } from 'app/shared/model/currency-rate.model';

export const ACTION_TYPES = {
  FETCH_CURRENCYRATE_LIST: 'currencyRate/FETCH_CURRENCYRATE_LIST',
  FETCH_CURRENCYRATE: 'currencyRate/FETCH_CURRENCYRATE',
  CREATE_CURRENCYRATE: 'currencyRate/CREATE_CURRENCYRATE',
  UPDATE_CURRENCYRATE: 'currencyRate/UPDATE_CURRENCYRATE',
  DELETE_CURRENCYRATE: 'currencyRate/DELETE_CURRENCYRATE',
  RESET: 'currencyRate/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICurrencyRate>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CurrencyRateState = Readonly<typeof initialState>;

// Reducer

export default (state: CurrencyRateState = initialState, action): CurrencyRateState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CURRENCYRATE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CURRENCYRATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CURRENCYRATE):
    case REQUEST(ACTION_TYPES.UPDATE_CURRENCYRATE):
    case REQUEST(ACTION_TYPES.DELETE_CURRENCYRATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CURRENCYRATE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CURRENCYRATE):
    case FAILURE(ACTION_TYPES.CREATE_CURRENCYRATE):
    case FAILURE(ACTION_TYPES.UPDATE_CURRENCYRATE):
    case FAILURE(ACTION_TYPES.DELETE_CURRENCYRATE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCYRATE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCYRATE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CURRENCYRATE):
    case SUCCESS(ACTION_TYPES.UPDATE_CURRENCYRATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CURRENCYRATE):
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

const apiUrl = 'api/currency-rates';

// Actions

export const getEntities: ICrudGetAllAction<ICurrencyRate> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CURRENCYRATE_LIST,
  payload: axios.get<ICurrencyRate>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICurrencyRate> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CURRENCYRATE,
    payload: axios.get<ICurrencyRate>(requestUrl)
  };
};

export const getByCurrency: ICrudGetAction<ICurrencyRate> = currency => {
  const requestUrl = `${apiUrl}/currency/${currency}`;
  return {
    type: ACTION_TYPES.FETCH_CURRENCYRATE,
    payload: axios.get<ICurrencyRate>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICurrencyRate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CURRENCYRATE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICurrencyRate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CURRENCYRATE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICurrencyRate> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CURRENCYRATE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
