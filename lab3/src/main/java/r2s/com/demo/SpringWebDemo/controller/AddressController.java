
package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;
import r2s.com.demo.SpringWebDemo.service.AddressService;

import java.util.List;

@RestController()
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        List<Address> addressList = addressService.getAllAddressDatabase();
        return new ResponseEntity(addressList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertAddress(@RequestBody InsertAddressRequestDTO requestDTO) {
        Address address = addressService.insertAddress(requestDTO);
        return new ResponseEntity(address, HttpStatus.OK);
    }
    
//    @GetMapping("/{address-id}")
//    public ResponseEntity getProductById(@PathVariable(value = "address-id") int addressId) {
//        List<AddressResponseDTO> response = addressService.getListAddress()
//                .stream()
//                .filter(c -> c.getId() == addressId)
//                .collect(Collectors.toList());
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//    @PostMapping
//    public ResponseEntity insertAddress(@RequestBody CreateAddressRequestDTO createAddressRequestDTO) {
//        int id = createAddressRequestDTO.getId();
//        String name = createAddressRequestDTO.getName();
//        String price = createAddressRequestDTO.getPhone();
//        String salerName = createAddressRequestDTO.getProvince();
//        String district = createAddressRequestDTO.getDistrict();
//        String street = createAddressRequestDTO.getStreet();
//        boolean type = createAddressRequestDTO.isType();
//        boolean defaultAddress = createAddressRequestDTO.isDefaultAddress();
//        boolean deleted = createAddressRequestDTO.isDeleted();
//        int addressId = createAddressRequestDTO.getAddressId();
//        StringBuilder stringBuilder = new StringBuilder();
//        String response = stringBuilder.append(id)
//                .append(name)
//                .append(price)
//                .append(salerName)
//                .append(district)
//                .append(street)
//                .append(type)
//                .append(defaultAddress)
//                .append(deleted)
//                .append(addressId)
//                .toString();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @PutMapping("/{address-id}")
//    public ResponseEntity updateAddress(@PathVariable(value = "address-id") int addressId,
//                                        @RequestBody UpdateAddressRequestDTO updateAddressRequestDTO) {
//        AddressResponseDTO response = new AddressResponseDTO();
//        response.setId(addressId);
//        response.setName(updateAddressRequestDTO.getName());
//        response.setPhone(updateAddressRequestDTO.getPhone());
//        response.setProvince(updateAddressRequestDTO.getProvince());
//        response.setDistrict(updateAddressRequestDTO.getDistrict());
//        response.setStreet(updateAddressRequestDTO.getStreet());
//        response.setType(updateAddressRequestDTO.isType());
//        response.setDefaultAddress(updateAddressRequestDTO.isDefaultAddress());
//        response.setAddressId(updateAddressRequestDTO.getAddressId());
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{address-id}")
//    public ResponseEntity deleteAddress(@PathVariable(value = "address-id") int addressId) {
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
