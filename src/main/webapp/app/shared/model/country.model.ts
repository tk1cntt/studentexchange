import { Moment } from 'moment';

export interface ICountry {
  id?: number;
  name?: string;
  enabled?: boolean;
  createAt?: Moment;
  updateAt?: Moment;
  regionId?: number;
}

export const defaultValue: Readonly<ICountry> = {
  enabled: false
};
