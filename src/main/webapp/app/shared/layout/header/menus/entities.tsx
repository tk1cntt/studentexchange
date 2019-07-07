import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.country" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/region">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.region" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.city" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/district">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.district" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ward">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.ward" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-profile">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userProfile" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-shipping-address">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userShippingAddress" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-history">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderHistory" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderItem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-package">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderPackage" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-package-history">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderPackageHistory" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-transaction">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderTransaction" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-comment">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderComment" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shopping-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.shoppingCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shopping-cart-item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.shoppingCartItem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/warehouse">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.warehouse" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/delivery">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.delivery" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/delivery-package">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.deliveryPackage" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shopping-cart-item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.shoppingCartItem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/district">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.district" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ward">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.ward" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/district">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.district" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ward">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.ward" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.country" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.city" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-shipping-address">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userShippingAddress" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-shipping-address">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userShippingAddress" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency-rate">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.currencyRate" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-transaction">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderTransaction" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.payment" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-balance">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userBalance" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shopping-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.shoppingCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-profile">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.userProfile" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderCart" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/order-item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.orderItem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shopping-cart">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.shoppingCart" />
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
