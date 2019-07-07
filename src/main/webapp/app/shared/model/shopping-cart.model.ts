import { Moment } from 'moment';
import { IShoppingCartItem } from 'app/shared/model//shopping-cart-item.model';

export interface IShoppingCart {
  id?: number;
  avatar?: string;
  aliwangwang?: string;
  depositAmount?: number;
  depositRatio?: number;
  serviceFee?: number;
  serviceFeeDiscount?: number;
  itemChecking?: boolean;
  itemWoodCrating?: boolean;
  shopId?: string;
  shopLink?: string;
  shopName?: string;
  shopNote?: string;
  website?: string;
  tallyFee?: number;
  totalAmount?: number;
  totalQuantity?: number;
  finalAmount?: number;
  createAt?: Moment;
  updateAt?: Moment;
  items?: IShoppingCartItem[];
  createByLogin?: string;
  createById?: number;
  updateByLogin?: string;
  updateById?: number;
}

export const defaultValue: Readonly<IShoppingCart> = {
  itemChecking: false,
  itemWoodCrating: false
};
