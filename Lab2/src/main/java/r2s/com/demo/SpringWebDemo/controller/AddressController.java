
package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.CreateAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingAdsressListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingCartListResponseDTO;
import r2s.com.demo.SpringWebDemo.service.AddressService;
import r2s.com.demo.SpringWebDemo.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/address")
public class AddressController {
    private AddressService addressService = new AddressService();

    @GetMapping
    public ResponseEntity getAllAddress(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "sort", required = false) String sort) {
        List<AddressResponseDTO> addressResponseDTOList = addressService.getListAddress();

        PagingAdsressListResponseDTO response = new PagingAdsressListResponseDTO();
        response.setAddressResponseDTOList(addressResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{address-id}")
    public ResponseEntity getProductById(@PathVariable(value = "address-id") int addressId) {
        List<AddressResponseDTO> response = addressService.getListAddress()
                .stream()
                .filter(c -> c.getId() == addressId)
                .collect(Collectors.toList());
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertAddress(@RequestBody CreateAddressRequestDTO createAddressRequestDTO) {
        int id = createAddressRequestDTO.getId();
        String name = createAddressRequestDTO.getName();
        String price = createAddressRequestDTO.getPhone();
        String salerName = createAddressRequestDTO.getProvince();
        String district = createAddressRequestDTO.getDistrict();
        String street = createAddressRequestDTO.getStreet();
        boolean type = createAddressRequestDTO.isType();
        boolean defaultAddress = createAddressRequestDTO.isDefaultAddress();
        boolean deleted = createAddressRequestDTO.isDeleted();
        int userId = createAddressRequestDTO.getUserId();
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append(id)
                .append(name)
                .append(price)
                .append(salerName)
                .append(district)
                .append(street)
                .append(type)
                .append(defaultAddress)
                .append(deleted)
                .append(userId)
                .toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{address-id}")
    public ResponseEntity updateAddress(@PathVariable(value = "address-id") int addressId,
                                        @RequestBody UpdateAddressRequestDTO updateAddressRequestDTO) {
        AddressResponseDTO response = new AddressResponseDTO();
        response.setId(addressId);
        response.setName(updateAddressRequestDTO.getName());
        response.setPhone(updateAddressRequestDTO.getPhone());
        response.setProvince(updateAddressRequestDTO.getProvince());
        response.setDistrict(updateAddressRequestDTO.getDistrict());
        response.setStreet(updateAddressRequestDTO.getStreet());
        response.setType(updateAddressRequestDTO.isType());
        response.setDefaultAddress(updateAddressRequestDTO.isDefaultAddress());
        response.setUserId(updateAddressRequestDTO.getUserId());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity deleteAddress(@PathVariable(value = "address-id") int addressId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
