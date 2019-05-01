/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "carService")
@ApplicationScoped
public class CarService {

    private final static String[] colours;

    private final static String[] brands;

    @SuppressWarnings("WeakerAccess")
    public static String[] items;

    private static boolean idColumnSelected;

    private static boolean yearColumnSelected;

    private static boolean brandColumnSelected;

    private static boolean colourColumnSelected;

    private static boolean priceColumnSelected;

    private static boolean soldColumnSelected;

    static {
        colours = new String[10];
        colours[0] = "Black";
        colours[1] = "White";
        colours[2] = "Green";
        colours[3] = "Red";
        colours[4] = "Blue";
        colours[5] = "Orange";
        colours[6] = "Silver";
        colours[7] = "Yellow";
        colours[8] = "Brown";
        colours[9] = "Maroon";

        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }

    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<Car>();
        for (int i = 0; i < size; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColour(), getRandomPrice(), getRandomSoldState()));
        }

        return list;
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColour() {
        return colours[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }

    private int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }

    private boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true : false;
    }

    public List<String> getColours() {
        return Arrays.asList(colours);
    }

    public List<String> getBrands() {
        return Arrays.asList(brands);
    }

    public String[] getSelectedItems() {
        System.out.println(">>>>> getSelectedItems = " + (Object) CarService.items);

        return CarService.items;
    }

    public void setSelectedItems(final String[] items) {
        CarService.items = items;

        System.out.println(">>>>> setSelectedItems items = " + (Object) CarService.items);
    }

    public void selectedItemsChanged(AjaxBehaviorEvent ajaxBehaviorEvent) {
        System.out.println(">>>>> selectedItemsChanged items length = " + CarService.items.length);

        CarService.idColumnSelected = false;
        CarService.yearColumnSelected = false;
        CarService.brandColumnSelected = false;
        CarService.colourColumnSelected = false;
        CarService.priceColumnSelected = false;
        CarService.soldColumnSelected = false;

        for (String arrElement : CarService.items) {
            System.out.println(">>>>> items = " + arrElement);

            switch (arrElement) {
                case "Id":
                    CarService.idColumnSelected = true;
                    break;
                case "Year":
                    CarService.yearColumnSelected = true;
                    break;
                case "Brand":
                    CarService.brandColumnSelected = true;
                    break;
                case "Colour":
                    CarService.colourColumnSelected = true;
                    break;
                case "Price":
                    CarService.priceColumnSelected = true;
                    break;
                case "Sold":
                    CarService.soldColumnSelected = true;
                    break;
                default:
            }
        }
    }

    public boolean isIdColumnRendered() {
        return CarService.idColumnSelected;
    }

    public boolean isYearColumnRendered() {
        return CarService.yearColumnSelected;
    }

    public boolean isBrandColumnRendered() {
        return CarService.brandColumnSelected;
    }

    public boolean isColourColumnRendered() {
        return CarService.colourColumnSelected;
    }

    public boolean isPriceColumnRendered() {
        return CarService.priceColumnSelected;
    }

    public boolean isSoldColumnRendered() {
        return CarService.soldColumnSelected;
    }
}
