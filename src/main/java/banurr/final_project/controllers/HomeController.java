package banurr.final_project.controllers;

import banurr.final_project.models.Product;
import banurr.final_project.services.CategoryService;
import banurr.final_project.services.FeatureService;
import banurr.final_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeatureService featureService;

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("categories",categoryService.allCategories());
        return "home";
    }

    @GetMapping("/admin")
    public String adminPanel()
    {
        return "admin_panel";
    }

    @GetMapping("/admin/category")
    public String adminCategory(Model model)
    {
        model.addAttribute("categories", categoryService.allCategories());
        return "admin_category";
    }

    @GetMapping("/admin/product")
    public String adminProduct(Model model)
    {
        model.addAttribute("products", productService.allProducts());
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("features", featureService.allFeatures());
        return "admin_product";
    }

    @GetMapping("/admin/feature")
    public String adminFeature(Model model)
    {
        model.addAttribute("features", featureService.allFeatures());
        return "admin_feature";
    }

    @GetMapping("/admin/users")
    public String adminUsers()
    {
        return "admin_user";
    }

    @GetMapping("/admin/comments")
    public String adminComments()
    {
        return "admin_comment";
    }

    @PostMapping("/removeFeature/{id}")
    public String removeFeature(@PathVariable(name = "id") Long id,
                                @RequestParam(name = "feature") Long feature)
    {

        Product product = productService.findProduct(id);
        product.getFeatures().remove(featureService.findFeature(feature));
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }
    @PostMapping("/addFeature/{id}")
    public String addFeature(@PathVariable(name = "id") Long id,
                                @RequestParam(name = "feature") Long feature)
    {
        Product product = productService.findProduct(id);
        product.getFeatures().add(featureService.findFeature(feature));
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/categories")
    public String allCategories(Model model)
    {
        model.addAttribute("categories", categoryService.allCategories());
        return "client_category";
    }
}

