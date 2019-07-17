import { SUCCESS } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  SHOW_DRAWER: 'setting/SHOW_DRAWER'
};

const initialState = {
  showDrawer: false
};

export type SettingState = Readonly<typeof initialState>;

export default (state: SettingState = initialState, action): SettingState => {
  switch (action.type) {
    case ACTION_TYPES.SHOW_DRAWER:
      return {
        ...state,
        showDrawer: action.payload
      };
    default:
      return state;
  }
};

export const showDrawer = value => async dispatch => {
  dispatch({
    type: ACTION_TYPES.SHOW_DRAWER,
    payload: value
  });
};
