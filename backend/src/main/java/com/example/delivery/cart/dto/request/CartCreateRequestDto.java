package com.example.delivery.cart.dto.request;

import com.example.delivery.cart.entity.Cart;
import com.example.delivery.menu.entity.Menu;
import com.example.delivery.menu.entity.MenuOption;
import com.example.delivery.user.entity.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateRequestDto {
    private UUID storeId;
    private UUID menuId;
    private List<UUID> menuOptionIdList;
    private int count;
    private Cart.CartStatus cartStatus;

    public Cart toEntity(User user, Menu menu, List<MenuOption> menuOptions, int menuPrice) {
        Cart cart = Cart.builder()
                .user(user)
                .menu(menu)
                .count(this.count == 0 ? 1 : this.count)
                .price(menuPrice)
                .cartStatus(Cart.CartStatus.PENDING)
                .build();

        cart.setMenuOptions(menuOptions);
        return cart;
    }
}
