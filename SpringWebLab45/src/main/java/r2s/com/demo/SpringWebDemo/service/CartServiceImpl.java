package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.mapper.CartMapper;
import r2s.com.demo.SpringWebDemo.repository.CartRepository;

import java.util.List;
@Component
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> getAllCartDatabase() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        return (List<Cart>) cartIterable;
    }
    @Override
    public PageResponseDTO getCartPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<Cart> cartPage = cartRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Cant get cart by paging"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(cartPage.getNumber());
        pageResponseDTO.setSize(cartPage.getSize());
        pageResponseDTO.setTotalPages(cartPage.getTotalPages());
        pageResponseDTO.setTotalRecord(cartPage.getTotalElements());

        List<CartResponseDTO> cartResponseDTOS = cartMapper.convertEntiitiesToResponseDtos(cartPage.getContent());
        pageResponseDTO.setData(cartResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Cart insertCart(InsertCartRequestDTO requestDTO) {
        Cart cart= new Cart();
        cart.setUserId(requestDTO.getUserId());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public CartResponseDTO getCartbyId(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant get cart by this id"));

        CartResponseDTO cartResponseDTOS = cartMapper.convertEntiitiesToResponseDto(cart);
        return cartResponseDTOS;
    }

    @Override
    @Transactional
    public CartResponseDTO updateCart(UpdateCartRequestDTO requestDTO, Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant update cart by this id"));
        cart.setUserId(requestDTO.getUserId());
        cartRepository.save(cart);
        CartResponseDTO cartResponseDTO = cartMapper.convertEntiitiesToResponseDto(cart);
        return cartResponseDTO;
    }

    @Override
    @Transactional
    public void deleteCartbyId(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant delete cart by this id"));

        cartRepository.delete(cart);
    }
}
