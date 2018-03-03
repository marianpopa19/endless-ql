﻿using QL.Core.Api;
using System.Windows;

namespace QL.Presentation
{
    public partial class MainWindow : Window
    {
        private MainController _controller;

        public MainWindow()
        {
            InitializeComponent();

            var viewModel = new MainViewModel(this.QuestionnaireHost);
            DataContext = viewModel;
            _controller = new MainController(viewModel, new FormFactory(ServiceRegistry.ParsingService));
        }
    }
}